public class DictionaryException extends RuntimeException {
	public DictionaryException()
	{
		super("Dictionary error: attempted to add an existing entry or remove a non-existant entry");
	}
}