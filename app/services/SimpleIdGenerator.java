package services;

public class SimpleIdGenerator implements IdGenerator{
    private static int id = 1;
    @Override
    public int getNextId() {
        return id++;
    }
}
