public class TrackingException extends Exception
{
    public TrackingFormatException()
    {
        super("Tracking Code doesn't exists");
    }

    public DateFormatException(String message)
    {
        super(message);
    }
}
