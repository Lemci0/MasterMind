import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameplayTest {

    @Test
    public void game_isCountingBlacksOk_resultTrue() throws Exception {

        //given
        //char[] answer = new char[4];
        char[] answer = {'c','z','n','p'};
        String guess = "cccc";

        //when
        Gameplay gp = new Gameplay();
        gp.game();

        //then
        assertEquals(2,gp.black);
    }


}