package hl.AngularJS;

public class formFieldText extends formField {

    public formFieldText(String label, String name) {
        super(label, name);
    }

    @Override
    public String toString() {
        String campo
                = "<div class=\"control-group\">\n"
                + "<label class=\"control-label\" " + getProp("for", getName()) + "\">" + getLabel() + "</label>\n"
                + "<div class=\"controls\">\n"
                + "<input "
                + getProp("type", "text") + " "
                + getProp("ng-model", getName()) + " "
                + getProp("id", getName())
                + getProp("class", "form-control")
                + " >\n"
                + "</div>\n"
                + "</div>\n";
        return campo;
    }

}
