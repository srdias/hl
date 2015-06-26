package hl.AngularJS;

public class formFieldText extends formField {

    public formFieldText(String label, String name) {
        super(label, name);
    }

    @Override
    public String toString() {
        String visivel = "";

        if (getName().endsWith(".id")) {
            putProp("ng-disabled", "true");
        }

        if (!isVisivel()) {
            visivel = getProp("ng-show", "false") + " ";
        }

        String campo
                = "<div " + visivel + " class=\"control-group\">\n"
                + "<label class=\"control-label\" " + getProp("for", getName()) + ">" + getLabel() + "</label>\n"
                + "<div class=\"controls\">\n"
                + "<input "
                + getProp("type", "text") + " "
                + getProp("ng-model", getName()) + " "
                + getProp("id", getName()) + " "
                + getProp("class", "form-control")
                + getProperties()
                + " >\n"
                + "</div>\n"
                + "</div>\n";
        return campo;
    }

}
