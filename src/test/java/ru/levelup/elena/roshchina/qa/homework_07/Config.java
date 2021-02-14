package ru.levelup.elena.roshchina.qa.homework_07;

import java.net.URL;

public abstract class Config {
    protected URL dataFilePathName = this.getClass().getResource("/ru.levelup.elena.roshchina.qa/homework_07/");
    protected final String APIToken = "e2735da4aa6087a53f5374a398698fbf5aee5eb48da6944b899cc433ce9e1d2a";
    protected final String userDataFileName = "user_data.csv";
    protected final String postDataFileName = "post_data.csv";
    protected final String commentsDataFileName = "comment_data.csv";
}
