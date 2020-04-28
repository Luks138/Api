package yardani.templates;

public class ErrorMessageEntity {

    private String errorMessage;

    public ErrorMessageEntity(String errorMessage) {
        setErrorMessage(errorMessage);
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
