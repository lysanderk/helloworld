package kf.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class BizFactory {
	private static Random r=new Random();
	public static List<Member>  initMemberPojo(int size){
		List<Member> members=new ArrayList<Member>();
		for(int i=0;i<size;i++){
			Member  member=new Member();
			member.setId(UUID.randomUUID().toString());
			member.setTier(C.MEMBER_TIER_LIST[r.nextInt(C.MEMBER_TIER_LIST.length)]);
			
			int tmp_size=r.nextInt(C.MEMBER_ACTION_LIST.length)*2;
			if(tmp_size==0)
				tmp_size=1;
			ArrayList actions=new ArrayList<String>();
			
			for(int j=0;j<tmp_size;j++){
				actions.add(C.MEMBER_ACTION_LIST[r.nextInt(C.MEMBER_ACTION_LIST.length)]);
			}
		 
			member.setActions(actions);
			members.add(member);
			System.out.println(member);
		}
		return members;
	}
	
	
	
	public static void main(String s[]){
		initMemberPojo(84);
	}
}
