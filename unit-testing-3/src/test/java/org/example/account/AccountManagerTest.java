package org.example.account;


import org.example.account.AccountManager;
import org.example.account.AccountManagerImpl;
import org.example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AccountManagerTest {

    @Test
    public void givenSufficentCustomerBalanceAndAmount_whenWithdraw_thenSucces(){
        //arrange
        Customer customer = Mockito.mock(Customer.class);
        Mockito.when(customer.getBalance()).thenReturn(8000);
        AccountManager accountManager = new AccountManagerImpl();
        //act
        String x= accountManager.withdraw(customer,200);

        //assert
        Mockito.verify(customer).setBalance(8000-200);
        Assertions.assertEquals(x,"success");
    }

    @Test
    public void givenUnsufficientBalanceWithcreditAllowedWithinRangeAndNotVip_whenWithdraw_thenSuccess(){
        //arrange
        Customer customer = Mockito.mock(Customer.class);
        Mockito.when(customer.getBalance()).thenReturn(0);
        Mockito.when(customer.isCreditAllowed()).thenReturn(true);
        Mockito.when(customer.isVip()).thenReturn(false);
        AccountManager accountManager = new AccountManagerImpl();

        //act
        String x = accountManager.withdraw(customer,200);
        //assert
        Assertions.assertEquals(x,"success");
    }


    @Test
    public void givenUnsufficientBalanceWithcreditAllowedNotWithinRangeAndNotVip_whenWithdraw_thenSuccess(){
        //arrange
        Customer customer = Mockito.mock(Customer.class);
        Mockito.when(customer.getBalance()).thenReturn(0);
        Mockito.when(customer.isCreditAllowed()).thenReturn(true);
        Mockito.when(customer.isVip()).thenReturn(false);
        AccountManager accountManager = new AccountManagerImpl();

        //act
        String x = accountManager.withdraw(customer,2000);
        //assert
        Assertions.assertEquals(x,"maximum credit exceeded");
    }

    @Test
    public void givenUnsufficientBalanceWithcreditAllowedNotWithinRangeAndVip_whenWithdraw_thenSuccess(){
        //arrange
        Customer customer = Mockito.mock(Customer.class);
        Mockito.when(customer.getBalance()).thenReturn(0);
        Mockito.when(customer.isCreditAllowed()).thenReturn(true);
        Mockito.when(customer.isVip()).thenReturn(true);
        AccountManager accountManager = new AccountManagerImpl();

        //act
        String x = accountManager.withdraw(customer,2000);
        //assert
        Assertions.assertEquals(x,"success");
    }
    @Test
    public void givenUnsufficientBalanceWithcreditNotAllowedNotWithinRangeAndVip_whenWithdraw_thenSuccess(){
        //arrange
        Customer customer = Mockito.mock(Customer.class);
        Mockito.when(customer.getBalance()).thenReturn(0);
        Mockito.when(customer.isCreditAllowed()).thenReturn(false);
        Mockito.when(customer.isVip()).thenReturn(false);
        AccountManager accountManager = new AccountManagerImpl();

        //act
        String x = accountManager.withdraw(customer,2000);
        //assert
        Assertions.assertEquals(x,"insufficient account balance");
    }
}



