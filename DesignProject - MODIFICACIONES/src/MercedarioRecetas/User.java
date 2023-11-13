package MercedarioRecetas;

public class User {

    private String userName;
    private String password;
    private USER_TYPE userType;
    private final boolean sesionIniciada = false;

    // Constructores
    public User() {
    }

    // Getter and Setter 
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public USER_TYPE getUserType() {
        return userType;
    }

    public void setUserType(USER_TYPE userType) {
        this.userType = userType;
    }

    // Método estático para crear un nuevo usuario
    public static User createUser(String userName, String password, USER_TYPE userType) {
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setPassword(password);
        newUser.setUserType(userType);
        return newUser;
    }

    // Método para autenticar al usuario
    public boolean authenticate(USER_TYPE userType, String password) {
        if (this.userType == userType) {
            if (isValidPassword(password)) {
                System.out.println("Autenticacion exitosa. BIENVENIDO!");
                return true;
            } else {
                System.out.println("Contrasena INVALIDA. Intentelo de nuevo.");
            }
        } else {
            System.out.println("Usuario INCORRECTO. Verifique su tipo de usuario.");
        }
        return false; // Autenticación fallida
    }

    // Método privado para verificar si la contraseña cumple con los criterios de validación
    private boolean isValidPassword(String password) {
        // Implementa tus criterios de validación de contraseña aquí
        return password.length() >= 8 && password.matches(".*[A-Za-z].*\\d.*");
    }

    public boolean isSesionIniciada() {
        return sesionIniciada;
    }


}
