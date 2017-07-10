package ttadm.util;

public enum Sortby {
		name("name"),
		code("code");
	
	 	private final String text;

	    /**
	     * @param text
	     */
	    private Sortby(final String text) {
	        this.text = text;
	    }
	
	    @Override
	    public String toString() {
	        return text;
	    }
}
