package com.example.boss.r3.helper;

/**
 * Created by sir on 3/13/2015.
 */
public class QueryBuilder {


    /**
     * Specify your database name here
     * @return
     */
    public String getDatabaseName() {
        return "code101";
    }

    /**
     * Specify your MongoLab API here
     * @return
     */
    public String getApiKey() {
        return "OxzI4NsK9OE50zAQz9e1k_BWNi-9mpEj";
    }

    /**
     * This constructs the URL that allows you to manage your database,
     * collections and documents
     * @return
     */
    public String getBaseUrl()
    {
        return "https://api.mongolab.com/api/1/databases/"+getDatabaseName()+"/collections/";
    }

    /**
     * Completes the formating of your URL and adds your API key at the end
     * @return
     */
    public String docApiKeyUrl()
    {
        return "?apiKey="+getApiKey();
    }

    /**
     * Returns the docs101 collection
     * @return
     */
    public String documentRequest()
    {
        return "docs101";
    }

    /**
     * Builds a complete URL using the methods specified above
     * @return
     */
    public String buildContactsSaveURL()
    {
        return getBaseUrl()+documentRequest()+docApiKeyUrl();
    }


   /* This method is identical to the one above.
            * @return
            */
    public String buildContactsGetURL()
    {
        return getBaseUrl()+documentRequest()+docApiKeyUrl();
    }

    /**
     * Formats the contact details for MongoHQ Posting
     * @param contact: Details of the person
     * @return
     */
    public String createContact(MyContact contact)
    {
        return String
                .format("{\"document\" : {\"first_name\": \"%s\", "
                                + "\"street\": \"%s\", \"apt\": \"%s\",\"np\": \"%s\", "
                                + "\"phone\": \"%s\",\"date\": \"%s\"}, \"safe\" : true}",
                        contact.first_name, contact.street, contact.apt,contact.np, contact.phone,contact.date);
    }




}
