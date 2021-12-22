public class KeyValue
{
	public String key, value;
	public boolean connection;
	
	public KeyValue()
	{
		key = new String();
		value = new String();
	}
	
	public int parseKeyValue(String keyValue)
	{
		keyValue = keyValue.replace("\" \"", "\"");
		String[] tokens = keyValue.split("\"");
		key = tokens[1];
		value = tokens[2];
		
		if (key.equals("mapversion") || key.startsWith("_tb"))
		{
			return 0;
		}
		if (key.startsWith("*"))
		{
			tokens = value.split(",");
			key = tokens[0];
			value = value.substring(value.indexOf(',') + 1);
			return 2;
		}
		
		return 1;
	}
	
	public void print()
	{
		System.out.printf("\t\"%s\" \"%s\"\n", key, value);
	}
}
