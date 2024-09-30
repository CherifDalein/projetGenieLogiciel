package re.forestier.edu;

import org.junit.jupiter.api.*;

import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class UnitTests {

    @Test
    @DisplayName("Sample test")
    void testPlayerName() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.removeMoney(200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

    @Test
    @DisplayName("Test adding money to player")
    void testAddMoney() {
        player p = new player("Alice", "Mage", "ADVENTURER", 100, new ArrayList<>());
        p.addMoney(50);
        assertThat(p.money, is(150));
    }
    
    @Test
    @DisplayName("Test removing money to player")
    void testRemoveMoney() {
        player p = new player("Alice", "Mage", "ADVENTURER", 100, new ArrayList<>());
        p.removeMoney(50);
        assertThat(p.money, is(50));
    }

    @Test
    @DisplayName("Test getting XP from player")
    void testGetXp() {
        player p = new player("Diana", "Ranger", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p, 20);
        assertThat(p.getXp(), is(20));
    }


    @Test
    @DisplayName("Test constructor with valid and invalid avatar class")
    void testConstructor() {
        player p1 = new player("John", "Warrior", "ADVENTURER", 150, new ArrayList<>());
        assertThat(p1.getAvatarClass(), is("ADVENTURER"));
        player p2 = new player("John", "Warrior", "DWARF", 150, new ArrayList<>());
        assertThat(p2.getAvatarClass(), is("DWARF"));
        player p3 = new player("John", "Warrior", "ARCHER", 150, new ArrayList<>());
        assertThat(p3.getAvatarClass(), is("ARCHER"));
        player p4 = new player("John", "Mage", "AUTRE", 200, new ArrayList<>());
        assertNull(p4.getAvatarClass()); 
    }


    @Test
    @DisplayName("Player level should be 1 when xp is less than 10")
    void testLevelOne() {
        player p = new player("Diana", "Ranger", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p, 5); // xp < 10
        assertThat(p.retrieveLevel(), is(1));
    }

    @Test
    @DisplayName("Player level should be 2 when xp is between 10 and 26")
    void testLevelTwo() {
        player p = new player("Diana", "Ranger", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p, 20); // 10 <= xp < 27
        assertThat(p.retrieveLevel(), is(2));
    }

    @Test
    @DisplayName("Player level should be 3 when xp is between 27 and 56")
    void testLevelThree() {
        player p = new player("Diana", "Ranger", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p, 40); // 27 <= xp < 57
        assertThat(p.retrieveLevel(), is(3));
    }

    @Test
    @DisplayName("Player level should be 4 when xp is between 57 and 110")
    void testLevelFour() {
        player p = new player("Diana", "Ranger", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p, 75); // 57 <= xp < 111
        assertThat(p.retrieveLevel(), is(4));
    }

    @Test
    @DisplayName("Player level should be 5 when xp is 111 or more")
    void testLevelFive() {
        player p = new player("Diana", "Ranger", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p, 120); // xp >= 111
        assertThat(p.retrieveLevel(), is(5));
    }





}
