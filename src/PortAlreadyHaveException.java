public class PortAlreadyHaveException extends Exception {
    public PortAlreadyHaveException() {
        super("В гавани уже есть такая лодка");
    }
}
