package top.dogtcc.test.server1.dao;

public class TestEntry {

    private TestEntry() {
    }

    private TestEntry next = null;

    public TestEntry getNext() {
        return next;
    }

    public void setNext(TestEntry next) {
        this.next = next;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    boolean success;

    public  static TestEntry OK(){

        TestEntry testEntry = new TestEntry();

        testEntry.setSuccess(true);

        return  testEntry;
    }

    public  static TestEntry Error(){

        TestEntry testEntry = new TestEntry();

        testEntry.setSuccess(false);

        return  testEntry;
    }

    public TestEntry next(TestEntry entry){

        this.setNext(entry);

        return  this;
    }

}
