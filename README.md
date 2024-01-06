This spring boot application show us how to perform the validation using the @Valid annotation.
Here is the steps:  
2.	Add the maven dependency  
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>  

3.	Use the @Valid anotation on your RequestBody on the controller layer  
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //@Valid annotation is used to validate incoming request
    @PostMapping("/fetch")
    public ResponseEntity<UserResponse> getDate(@RequestBody @Valid UserDto userDto){
        var response =userService.getData(userDto);
        log.info("UserController->getDate():response {}",response);
        return ResponseEntity.ok(response);
    }  

4.	Add the validation contraints on your model  

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collation = "users")
public class UserDto implements Serializable {
   // @Valid
    @NotBlank(message = "Invalid username: Empty username")
   // @NotNull(message = "Invalid username: username is NULL")
    @Size(min = 3, max = 30, message = "Invalid username: Must be of 3 - 30 characters")
    private String username;
    @NotBlank(message = "password is mandatory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[_#$%]).{8,}$",
            message = "Invalid password: Must contain at least: 1 number, 1 capital letter, 1special character _#$%")
    private String password;
    @NotBlank(message = "ip address is mandatory")
    private String ipAddress;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email")
    private String email;
}

@NotNull, @NotEmpty, and @NotBlank  (https://www.baeldung.com/java-bean-validation-not-null-empty-blank)  

5.	Handling the MethodArgumentNotValidException
@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}

