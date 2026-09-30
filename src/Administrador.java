public class Administrador {

    private String id;
    private String password;

    public Administrador(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public boolean autenticar(String id, String password) {
        return this.id.equals(id) && this.password.equals(password);
    }

    public String getId() {
        return id;
    }
}