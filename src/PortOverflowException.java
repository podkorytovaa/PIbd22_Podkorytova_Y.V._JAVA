public class PortOverflowException extends Exception {
    public PortOverflowException() {
        super("В гавани нет свободных мест");
    }
}
