package yardani.templates;

public class ErrorMessageEntity {

    private String errorMessage;
    private int errorCode;

    public ErrorMessageEntity(String errorMessage, int errorCode) {
        setErrorMessage(errorMessage);
        setErrorCode(errorCode);
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
