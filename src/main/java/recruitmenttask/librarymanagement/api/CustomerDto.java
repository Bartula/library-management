package recruitmenttask.librarymanagement.api;

public class CustomerDto {

    private String name;

    public CustomerDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void validation(){
        if (name == null || name.trim().isEmpty())
            throw new InvalidRequestException("Customer name can not be empty");
    }
}
