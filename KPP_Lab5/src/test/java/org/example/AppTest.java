package org.example;

import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class AppTest {
    @Test
    public void ShouldReturnListWith3Elements() {
        List<String> list = App.getThreeWords(" Happy New Year ");
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void ShouldReturnListWithout3Elements() {
        List<String> list1 = App.getThreeWords(" Happy Audi");
        List<String> list2 = App.getThreeWords(" New Year Ethereum");
        Assert.assertEquals(2, list1.size() + list2.size());
    }

    @Test
    public void ShouldSaveFirstWord() {
        String result = App.removeWordsWithCertainLength("word words robot ", 5);
        Assert.assertTrue(result.contains("word"));
        Assert.assertFalse(result.contains("words"));
        Assert.assertFalse(result.contains("robot"));
    }

    @Test
    public void ShouldDeleteSecWord() {
        String result = App.removeWordsWithCertainLength("smb, Delete. smth", 6);
        Assert.assertTrue(result.contains("smb"));
        Assert.assertFalse(result.contains("Delete"));
        Assert.assertTrue(result.contains("smth"));
    }

    @Test
    public void ShouldDeleteAllWords() {
        String result = App.removeWordsWithCertainLength("Smells spirit", 6);
        Assert.assertFalse(result.contains("Smells"));
        Assert.assertFalse(result.contains("spirit"));
    }
}
