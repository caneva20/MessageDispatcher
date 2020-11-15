package me.caneva20.messagedispatcher.configurations;

public class TokenConfiguration implements IConfiguration {
    private String tag;
    private String debugTag;
    private String pluginNameParam;

    public String getTag() {
        return tag;
    }

    public String getDebugTag() {
        return debugTag;
    }

    public String getPluginNameParam() {
        return pluginNameParam;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setDebugTag(String debugTag) {
        this.debugTag = debugTag;
    }

    public void setPluginNameParam(String pluginNameParam) {
        this.pluginNameParam = pluginNameParam;
    }
}