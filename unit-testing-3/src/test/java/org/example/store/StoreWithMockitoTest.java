package org.example.store;

import org.example.account.AccountManager;
import org.example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



public class StoreWithMockitoTest {

    @Test
    public void givenProductWithPositiveQuantityAndCustomerWithSufficientBalance_whenBuy_thenSuccess() {

        // Arrange
        Product product = new Product();
        product.setPrice(100);
        product.setQuantity(4);

        Customer customer = new Customer();

        AccountManager accountManager = Mockito.mock(AccountManager.class);
        Mockito.when(accountManager.withdraw(customer, 80))
                .thenReturn("success");

        Mockito.when(accountManager.withdraw(customer, 100))
                .thenReturn("success");

        Store store = new StoreImpl(accountManager);

        // Act
        store.buy(product, customer);

        // Assert
        Assertions.assertEquals(3, product.getQuantity());
        Mockito.verify(accountManager).withdraw(customer, 100);
    }

    static class AlwaysSuccessAccountManager implements AccountManager {

        @Override
        public void deposit(Customer customer, int amount) {

        }

        @Override
        public String withdraw(Customer customer, int amount) {
            return "success";
        }
    }

    @Test
    public void givenProductWithZeroQuantityAndCustomerWithSufficcientBalance_whenBuy_thenThrowException(){
        //arrange
        Product product = new Product();
        product.setQuantity(0);
        product.setPrice(200);

        Customer customer = new Customer();

        AccountManager accountManager = Mockito.mock(AccountManager.class);
        Store store = new StoreImpl(accountManager);
        //act

        //Assert
        Assertions.assertThrows(RuntimeException.class,
                ()->store.buy(product,customer));

    }

    @Test
    public void givenProductWithNegativeQuantityAndCustomerWithSufficcientBalance_whenBuy_thenThrowException(){
        //arrange
        Product product = new Product();
        product.setQuantity(-5);
        product.setPrice(200);

        Customer customer = new Customer();

        AccountManager accountManager = Mockito.mock(AccountManager.class);
        Mockito.when(accountManager.withdraw(customer,product.getPrice()))
                .thenReturn("success");
        Store store = new StoreImpl(accountManager);
        //act

        //Assert
        Assertions.assertThrows(RuntimeException.class,
                ()->store.buy(product,customer));
    }

    @Test
    public void givenProductWithPositiveQuantityAndCustomerWithUnsufficcientBalance_whenBuy_thenThrowException(){
        //arrange
        Product product = new Product();
        product.setQuantity(5);
        product.setPrice(200);

        Customer customer = new Customer();

        AccountManager accountManager = Mockito.mock(AccountManager.class);
        Mockito.when(accountManager.withdraw(customer,product.getPrice()))
                .thenReturn("ld,fsld,fsdl");
        Store store = new StoreImpl(accountManager);
        //act

        //Assert
        Assertions.assertThrows(RuntimeException.class,
                ()->store.buy(product,customer));
    }
}
