package stackoverflow;

import java.util.ArrayList;


public class QuestionEntry 
{
    String question;
    String postedby;
	String posttime;
    String title;
    ArrayList<String> tagList;
    int questionNumber;
    //AnswerList answer;
    
    public QuestionEntry( String question, String title, String postedby, String posttime, ArrayList<String> tag, int questionNumber)
    {
        this.question = question;
        this.postedby = postedby;
        this.posttime = posttime;
        this.tagList = tag;
        this.title = title;
        this.questionNumber = questionNumber;
        //answer = null;
    }
    
    /*public void setAnswer(AnswerList answer)
    {
    	this.answer = answer;
    }*/

	public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getPostedby() {
		return postedby;
	}

	public void setPostedby(String postedby) {
		this.postedby = postedby;
	}

	public String getPosttime() {
		return posttime;
	}

	public void setPosttime(String posttime) {
		this.posttime = posttime;
	}
    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<String> getTagList() {
		return tagList;
	}

	public void setTagList(ArrayList<String> tag) {
		this.tagList = tag;
	}

    
}
