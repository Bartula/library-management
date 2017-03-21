package recruitmenttask.librarymanagement.api;

public class CustomerDto {

    private String name;

    public CustomerDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void validate(){
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Customer name can not be empty");
    }
}
