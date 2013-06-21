/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mission;

import Main.AssembleInterface;
import Main.GameElement;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Gregor
 */
abstract public class Mission extends GameElement implements AssembleInterface
{

    private String type;
    private ArrayList<String> necessaryFields;

    public Mission()
    {
        super();
        this.type = "";
        this.necessaryFields = new ArrayList<>();
        this.necessaryFields.add("id");
        this.addCompletenessVariable(this.idProperty());
    }

    public Mission(String type)
    {
        super();
        this.type = type;
        this.necessaryFields = new ArrayList<>();
        this.necessaryFields.add("id");
        this.addCompletenessVariable(this.idProperty());
    }

    @Override
    public Boolean isComplete()         // TODO: rework
    {
        Boolean isComplete = true;
//        for (Object object : this.getCompletenessArrayList()) {
////            System.out.println(object.getClass());
////            if (object instanceof ArrayList) {            // TODO
////                for (Object obj : ((ArrayList) object).toArray()) {
////                    if (obj instanceof AssembleInterface) {
////                        if (((AssembleInterface) obj).isComplete()) {
////                            isComplete = false;
////                        }
////                        else if (obj instanceof Property) {
////                            if (((Property) obj).getValue() == null) {
////                                isComplete = false;
////                            }
////                        }
////                    }
////                }
////            }
//            if (object instanceof AssembleInterface) {
//                if (((AssembleInterface) object).isComplete()) {
//                    isComplete = false;
//                }
//            }
//            else if (object instanceof Property) {
//                if (((Property) object).getValue() == null) {
//                    isComplete = false;
//                }
//            }
//        }
        return isComplete;
    }

    public ArrayList<String> getNecessaryFields()
    {
        return necessaryFields;
    }

    public void addNecessaryField(String fieldName)
    {
        this.necessaryFields.add(fieldName);
    }

    public void addNecessaryField(String... fields)
    {
        this.necessaryFields.addAll(Arrays.asList(fields));
    }

    public String getType()
    {
        return this.type;
    }

    public String createMissionHeader()
    {
        return "<mission" + utilitys.type(this.type) + utilitys.id(this.getId()) + createSpecificMissionHeader() + ">";
    }

    public String createMissionTrailer()
    {
        return "</mission>";
    }

    public abstract String createSpecificMissionHeader();
}
