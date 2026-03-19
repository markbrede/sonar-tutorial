package edu.byu.cs.sonar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class CustomFileReaderTest {

    private CustomFileReader sut;

    @BeforeEach
    void setUp() {
        sut = new CustomFileReader("readMe1.txt");
    }

    @Test
    void testHowManyWordsInFile() throws FileNotFoundException {
        assertEquals(4, sut.howManyWordsInFile(), "There should be 4 words in readMe1.txt");
    }

    @Test
    void testReturnThatWord() throws FileNotFoundException {
        assertEquals("I", sut.returnThatWord(1), "The first word should be I in readMe1.txt");
    }

    @Test
    void testFindNewWord() throws FileNotFoundException {
        sut.findNewWord("C");
        assertEquals("Computer ", sut.getNewSentence(), "Computer should be found when C queried");
    }

    @Test
    void testGetNewSentence() {
        assertEquals("", sut.getNewSentence(), "New sentence should be empty initially");
    }

    @Test
    void setNewSentence() {
        final String betterSentence = "New Sentence.";
        sut.setNewSentence(betterSentence);
        assertEquals(betterSentence, sut.getNewSentence());
    }

    @Test
    void countTwice() throws FileNotFoundException {
        assertEquals(4, sut.howManyWordsInFile());
        assertEquals(4, sut.howManyWordsInFile());
    }

    @Test
    void sameReader() {
        final CustomFileReader other = new CustomFileReader("readMe1.txt");
        assertEquals(sut, other);
    }

    @Test
    void differentSentence() {
        final CustomFileReader other = new CustomFileReader("readMe1.txt");
        other.setNewSentence("Different");
        assertNotEquals(sut, other);
    }

    @Test
    void differentFile() {
        final CustomFileReader other = new CustomFileReader("readMe2.txt");
        assertNotEquals(sut, other);
    }

    @Test
    void differentCount() throws FileNotFoundException {
        final CustomFileReader other = new CustomFileReader("readMe1.txt");
        other.howManyWordsInFile();

        sut.setNewSentence("Same");
        other.setNewSentence("Same");

        assertNotEquals(sut, other);
    }

    @Test
    void textOut() {
        sut.setNewSentence("Hello");
        assertEquals("Hello 0", sut.toString());
    }

    @Test
    void hashZero() {
        sut.setNewSentence("7");
        assertEquals(0, sut.hashCode());
    }

    @Test
    void hashCount() throws FileNotFoundException {
        sut.howManyWordsInFile();
        sut.setNewSentence("7");
        assertEquals(84, sut.hashCode());
    }

    @Test
    void sameAfterCount() throws FileNotFoundException {
        final CustomFileReader other = new CustomFileReader("readMe1.txt");
        sut.howManyWordsInFile();
        other.howManyWordsInFile();
        assertEquals(sut, other);
    }
}