import model.WithdrawResponse;
import org.junit.Assert;
import org.junit.Test;
import service.CashService;

import java.util.ArrayList;
import java.util.List;

public class CashServiceTest {

    //Test unitario, unit test.
    @Test
    public void teste() throws Exception {
        CashService cashService = new CashService();
        List<WithdrawResponse> processWithdraw = cashService.processWithdraw(20);
        WithdrawResponse withdrawResponse = processWithdraw.get(0);
        int amountNotes = withdrawResponse.getAmountNotes();
        Assert.assertEquals(1, amountNotes);
        Assert.assertEquals(20, withdrawResponse.getNote());

    }

    @Test
    public void teste2() throws Exception {
        CashService cashService = new CashService();
        List<WithdrawResponse> processWithdraw = cashService.processWithdraw(30);
        WithdrawResponse withdrawResponse = processWithdraw.get(0);
        WithdrawResponse withdrawResponse1 = processWithdraw.get(1);
        int amountNotes = withdrawResponse.getAmountNotes();
        int amountNotes1 = withdrawResponse1.getAmountNotes();
        Assert.assertEquals(1, amountNotes);
        Assert.assertEquals(20, withdrawResponse.getNote());
        Assert.assertEquals(1, amountNotes1);
        Assert.assertEquals(10, withdrawResponse1.getNote());
    }
}