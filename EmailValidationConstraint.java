@Component
@RequiredArgsConstructor
public class EmailValidationConstraint implements ConstraintValidator<EmailValidation, String> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(EmailValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (repository != null){
            System.out.println(repository);
            boolean existe = this.repository.existsByEmail(s);
            System.out.println(existe);
            return !existe;
        }
        System.out.println("No encontre repository");
        return false;
    }