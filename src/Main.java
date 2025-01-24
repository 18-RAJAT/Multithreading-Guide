public class Main {
    public static void main(String[] args)
    {
        BankAccount bankaccount=new BankAccount();
//        Runnable task=new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                bankaccount.withdraw(1005);
//            }
//        };
//        Thread t1=new Thread(task,"Task1");
//        Thread t2=new Thread(task,"Task2");
//        Thread t3=new Thread(task,"Task3");


        //another way
        Thread t1=new Thread(()-> bankaccount.withdraw(2000),"Task1");
        Thread t2=new Thread(()-> bankaccount.withdraw(1974),"Task2");
        Thread t3=new Thread(()-> bankaccount.withdraw(1791),"Task3");
        Thread t4=new Thread(()-> bankaccount.withdraw(-1),"Task4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
