package RdF;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Visualizer implements Runnable
{
   private ObjectOutputStream uiout;
   private String s;
   private String t;
   private Comands co;
   private char c;
   private ArrayList<Integer> list;
   private int manchen,points;
   private boolean solut=false;
   private HashMap<String,Integer> ppos;
   private String move;
    public Visualizer(ObjectOutputStream uiou, String sent, String theme, int com, ArrayList<Integer> listI, int manche, char ch, boolean so, HashMap<String,Integer> ppos,int point,String mo)
    {
    	move=mo;
    	points=point;
    	this.ppos=ppos;
        uiout=uiou;
        s=sent;
        t=theme;
        co=getCombyCode(com);
        list=listI;
        manchen=manche;
        c=ch;
        solut=so;
    }

	public static Comands getCombyCode(int i) {
		Comands com = null;
		for (Comands c : Comands.values()) {
			if (i == c.getCode())
				return c;
		}
		return com;
	}

    @Override
    public void run()
    {
        try {
        	System.out.println(co);
			uiout.writeObject(co);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		switch(co)
		{
		case VOWEL:
			case CONS:
				try {
					String temp=c+"";
					for(Integer i:list)
						System.out.println(i);
			uiout.writeObject(temp);
			uiout.writeObject(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
		case SOLUT:
			if(solut)
			try {
				uiout.writeObject(s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			else
				try {
					uiout.writeObject("");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			case NEW:
				try {
					uiout.writeObject(t);
					uiout.writeObject(manchen);
					String[] temp=new String[3];
					String[] names=ppos.keySet().toArray(new String[ppos.keySet().size()]);
					for(int i=0;i<3;i++)
					{
						if(i==0)
							temp[i]=names[i];
						else
							if(ppos.get(temp[i-1])<ppos.get(names[i]))
							{
								temp[i]=temp[i-1];
								if(i==2)
								{
									if (ppos.get(temp[i - 2]) < ppos.get(names[i])) {
										temp[i - 1] = temp[i - 2];
										temp[i - 2] = names[i];
									} else {
										temp[i - 1] = names[i];
									}
								}
								else
									temp[i-1]=names[i];
							}
							else
								temp[i]=names[i];
					}
					for(int i=0;i<3;i++)
						temp[i]=temp[i].concat(" "+ppos.get(temp[i]));
					uiout.writeObject(temp);
					uiout.writeObject(points);
					uiout.writeObject(move);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
			break;
		}
    }


}
