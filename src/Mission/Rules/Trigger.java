/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtestapplication1;

import java.util.ArrayList;

/**
 *
 * @author Gregor
 */
public class Trigger implements AssembleInterface
{
    private String trigger;
    private ArrayList<Rule> rules;

    public Trigger()
    {
        this.trigger = "onStart";
        this.rules = new ArrayList<>();
    }
    
    public Trigger(String event)
    {
        this.trigger = event;
        this.rules = new ArrayList<>();
    }
    
    public void addRule(Rule rule)
    {
        this.rules.add(rule);
    }
    
    public void addRule(Rule rule, int position)
    {
        this.rules.add(rule);
    }

    @Override
    public String assemble()
    {
        return "<" + trigger + ">" + utilitys.createStringFromArrayList(rules) + "</" + trigger + ">";
    }
}
