import model.WithdrawResponse;
import org.junit.Assert;
import org.junit.Test;
import service.CashService;

import java.util.ArrayList;
import java.util.List;

public class CashServiceTest {

    @Test
    public void itShouldReturn1NoteOf10GivenUserInputIs10() throws Exception {
        CashService cashService = new CashService();
        List<WithdrawResponse> processWithdraw = cashService.processWithdraw(10);
        WithdrawResponse withdrawResponse = processWithdraw.get(0);
        int amountNotes = withdrawResponse.getAmountNotes();
        Assert.assertEquals(1, amountNotes);
        Assert.assertEquals(10, withdrawResponse.getNote());
    }

    @Test
    public void itShouldReturn1NoteOf20GivenUserInputIs20() throws Exception {
        CashService cashService = new CashService();
        List<WithdrawResponse> processWithdraw = cashService.processWithdraw(20);
        WithdrawResponse withdrawResponse = processWithdraw.get(0);
        int amountNotes = withdrawResponse.getAmountNotes();
        Assert.assertEquals(1, amountNotes);
        Assert.assertEquals(20, withdrawResponse.getNote());
    }

    @Test
    public void itShouldReturn1NoteOf20And1NoteOf10GivenUserInputIs30() throws Exception {
        CashService cashService = new CashService();
        List<WithdrawResponse> processWithdraw = cashService.processWithdraw(30);
        WithdrawResponse withdrawResponse = processWithdraw.get(0);
        WithdrawResponse withdrawResponse1 = processWithdraw.get(1);
        int amountNotesOf20 = withdrawResponse.getAmountNotes();
        int amountNotesOf10 = withdrawResponse1.getAmountNotes();
        Assert.assertEquals(1, amountNotesOf20);
        Assert.assertEquals(20, withdrawResponse.getNote());
        Assert.assertEquals(1, amountNotesOf10);
        Assert.assertEquals(10, withdrawResponse1.getNote());
    }

    @Test
    public void itShouldReturn2NotesOf20GivenUserInputIs40() throws Exception {
        CashService cashService = new CashService();
        List<WithdrawResponse> processWithdraw = cashService.processWithdraw(40);
        WithdrawResponse withdrawResponse = processWithdraw.get(0);
        int amountNotesOf20 = withdrawResponse.getAmountNotes();
        Assert.assertEquals(2, amountNotesOf20);
        Assert.assertEquals(20, withdrawResponse.getNote());
        Assert.assertEquals(20, withdrawResponse.getNote());
    }

    @Test
    public void itShouldReturn1NoteOf50GivenUserInputIs50() throws Exception {
        CashService cashService = new CashService();
        List<WithdrawResponse> processWithdraw = cashService.processWithdraw(50);
        WithdrawResponse withdrawResponse = processWithdraw.get(0);
        int amountNotesOf50 = withdrawResponse.getAmountNotes();
        Assert.assertEquals(1, amountNotesOf50);
        Assert.assertEquals(50, withdrawResponse.getNote());
    }

    @Test
    public void itShouldReturn1NoteOf50And1NoteOf10GivenUserInputIs60() throws Exception {
        CashService cashService = new CashService();
        List<WithdrawResponse> processWithdraw = cashService.processWithdraw(60);
        WithdrawResponse withdrawResponse = processWithdraw.get(0);
        WithdrawResponse withdrawResponse1 = processWithdraw.get(1);
        int amountNotesOf50 = withdrawResponse.getAmountNotes();
        int amountNotesOf10 = withdrawResponse1.getAmountNotes();
        Assert.assertEquals(1, amountNotesOf50);
        Assert.assertEquals(50, withdrawResponse.getNote());
        Assert.assertEquals(1, amountNotesOf10);
        Assert.assertEquals(10, withdrawResponse1.getNote());
    }

    @Test
    public void itShouldReturn1NoteOf50And1NoteOf20GivenUserInputIs70() throws Exception {
        CashService cashService = new CashService();
        List<WithdrawResponse> processWithdraw = cashService.processWithdraw(70);
        WithdrawResponse withdrawResponse = processWithdraw.get(0);
        WithdrawResponse withdrawResponse1 = processWithdraw.get(1);
        int amountNotesOf50 = withdrawResponse.getAmountNotes();
        int amountNotesOf20 = withdrawResponse1.getAmountNotes();
        Assert.assertEquals(1, amountNotesOf50);
        Assert.assertEquals(50, withdrawResponse.getNote());
        Assert.assertEquals(1, amountNotesOf20);
        Assert.assertEquals(20, withdrawResponse1.getNote());
    }

    @Test
    public void itShouldReturn1NoteOf50And1NoteOf20And1NoteOf10GivenUserInputIs80() throws Exception {
        CashService cashService = new CashService();
        List<WithdrawResponse> processWithdraw = cashService.processWithdraw(80);
        WithdrawResponse withdrawResponse = processWithdraw.get(0);
        WithdrawResponse withdrawResponse1 = processWithdraw.get(1);
        WithdrawResponse withdrawResponse2 = processWithdraw.get(2);
        int amountNotesOf50 = withdrawResponse.getAmountNotes();
        int amountNotesOf20 = withdrawResponse1.getAmountNotes();
        int amountNotesOf10 = withdrawResponse1.getAmountNotes();
        Assert.assertEquals(1, amountNotesOf50);
        Assert.assertEquals(50, withdrawResponse.getNote());
        Assert.assertEquals(1, amountNotesOf20);
        Assert.assertEquals(20, withdrawResponse1.getNote());
        Assert.assertEquals(1, amountNotesOf10);
        Assert.assertEquals(10, withdrawResponse2.getNote());
    }

    @Test
    public void itShouldReturn1NoteOf50And2NotesOf20GivenUserInputIs90 () throws Exception {
        CashService cashService = new CashService();
        List<WithdrawResponse> processWithdraw = cashService.processWithdraw(90);
        WithdrawResponse withdrawResponse = processWithdraw.get(0);
        WithdrawResponse withdrawResponse1 = processWithdraw.get(1);
        int amountNotesOf50 = withdrawResponse.getAmountNotes();
        int amountNotesOf20 = withdrawResponse1.getAmountNotes();
        Assert.assertEquals(1, amountNotesOf50);
        Assert.assertEquals(50, withdrawResponse.getNote());
        Assert.assertEquals(2, amountNotesOf20);
        Assert.assertEquals(20, withdrawResponse1.getNote());
    }

    @Test
    public void itShouldReturn1NoteOf100GivenUserInputIs100() throws Exception {
        CashService cashService = new CashService();
        List<WithdrawResponse> processWithdraw = cashService.processWithdraw(100);
        WithdrawResponse withdrawResponse = processWithdraw.get(0);
        int amountNotesOf100 = withdrawResponse.getAmountNotes();
        Assert.assertEquals(1, amountNotesOf100);
        Assert.assertEquals(100, withdrawResponse.getNote());
    }
}