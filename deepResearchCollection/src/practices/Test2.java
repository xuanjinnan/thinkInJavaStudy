package practices;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import examples.Countries;

class ABegainCoutries extends AbstractMap<String,String>{
	@Override
	public Set<Entry<String, String>> entrySet() {
		Set<Entry<String,String>> set = new HashSet<Entry<String,String>>();
		List<String> names = Countries.names();
		for(String name : names) {
			if(name.startsWith("A")) {
				set.add(new Map.Entry<String,String>() {

					@Override
					public String getKey() {
						return name;
					}

					@Override
					public String getValue() {
						return Countries.capitals().get(name);
					}

					@Override
					public String setValue(String value) {
						throw new UnsupportedOperationException();
					}
					
				});
			}
		}
		return set;
	}
	
}
public class Test2 {
	public static void main(String[] args) {
		//System.out.println(new ABegainCoutries());
		Map<String,String> hm = new HashMap<String,String>();
		Set<String> hs = hm.keySet();
		Pattern p = Pattern.compile("A[a-zA-Z]*");
		for(int i = 0; i < Countries.DATA.length; i++) {
			if(p.matcher(Countries.DATA[i][0]).matches()) {
				hm.put(Countries.DATA[i][0], Countries.DATA[i][1]);
			}
		}
		System.out.println(hm);
		System.out.println(hs);
	}
}
