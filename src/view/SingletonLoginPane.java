package view;

public class SingletonLoginPane extends AbstractLoginPane {

    private static Integer countInstances = 0;
    private static final Integer THRESHOLD = 1;


    public SingletonLoginPane() {
        super();
    }

    public static LoginPane getInstance() {
        if (countInstances < THRESHOLD) {
            countInstances++;
            return new LoginPane();
        }
        return getInstance();
    }
}
