package io.elopojo;

import org.junit.Test;

import java.io.IOException;

/**
 *
 * Created by ericramos on 26/05/17.
 */
public class EloPojoTest {

    @Test
    public void validateArgumetTestComplete(){
        String[] args = {"-p",  "names.txt", "-m", "matches.txt", "-r", "rank", "-k", "32"};
        EloPojo.validateParameters(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateArgumetRequiredTest(){
        String[] args = {"rank", "-k", "32"};
        EloPojo.validateParameters(args);

    }

    @Test
    public void mainTest() throws Exception {
        String[] args = {"-p",  "names.txt", "-m", "matches.txt", "-r", "rank", "-k", "32"};
        EloPojo.main(args);

    }

    @Test
    public void mainWithoutPlayerNameTest() throws Exception {

        try {
            String[] args = {"-p", "names.txt", "-m", "matches.txt", "-r", "player", "-k", "32"};

            EloPojo.main(args);
        }catch (IllegalArgumentException e){
            //expected exception
            assert true;
        }
    }


    @Test
    public void mainWithPlayerNameTest() throws Exception {
        String[] args = {"-p",  "names.txt", "-m", "matches.txt", "-r", "player", "-player_name", "Jacquelynn", "-k", "32"};
        EloPojo.main(args);

    }


}