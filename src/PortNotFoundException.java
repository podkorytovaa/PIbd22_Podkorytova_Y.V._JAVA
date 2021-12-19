public class PortNotFoundException extends Exception {
    public PortNotFoundException(int i) {
        super("Не найдена лодка по месту " + i);
    }
}
