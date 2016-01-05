package MemoryTest;

/**
 * Created by emaktse on 05.01.2016.
 */
public class PlayerOld {

    // Age validation method
    public boolean validateAge(String inputAge, String inputName){
        MainOld mainOld = new MainOld();
        errorPopUp error = new errorPopUp();
        try {
            int age = Integer.parseInt(inputAge);
            System.out.println("Name is " + inputName + ", Age is " + age);
            mainOld.selectLevel(1);

            return true;
        } catch (NumberFormatException e) {
            error.errorPop("Title", "Message");
            return false;
        }
    }
}
