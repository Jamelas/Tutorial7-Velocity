package templatesTutorial;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.List;


public class PrintInvitations {
    public static void main(String[] args) throws Exception {

        //read data from friends.xml
        XMLDecoder d = new XMLDecoder(
                new BufferedInputStream(
                        new FileInputStream("friends.xml")));
        List<Person> persons = (List<Person>) d.readObject();
        d.close();

        VelocityContext context = new VelocityContext();
        Template template = Velocity.getTemplate("letter.vm");

        FileWriter out = new FileWriter("invitations.txt");
        for(Person p : persons) {
            context.put("p", p);
            template.merge(context, out);
        }
        out.close();
    }
}
