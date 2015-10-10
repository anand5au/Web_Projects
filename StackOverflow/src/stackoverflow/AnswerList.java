package stackoverflow;

public class AnswerList 
{
	int answerId;
	String answer;
	String posted;
    String posttime;
    String title;
    int positiveVoteCount;
    int negativeVoteCount;
    String accepted;
    
    public AnswerList(int answerId, String answer, String postedby, String posttime, String title,int pvote, int nvote, String accepted) 
    {
		this.answer = answer;
		this.posted = postedby;
		this.posttime = posttime;
		this.title = title;
		this.answerId = answerId;
		this.positiveVoteCount=pvote;
		this.negativeVoteCount=nvote;
		this.accepted = accepted;
    }
    

	public String getAccepted() 
	{
		return accepted;
	}


	public void setAccepted(String accepted) 
	{
		this.accepted = accepted;
	}


	public int getPositiveVoteCount() {
		return positiveVoteCount;
	}

	public int getNegativeVoteCount() {
		return negativeVoteCount;
	}

	public void setPositiveVoteCount(int positiveVoteCount) {
		this.positiveVoteCount = positiveVoteCount;
	}

	public void setNegativeVoteCount(int negativeVoteCount) {
		this.negativeVoteCount = negativeVoteCount;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getPosted() 
	{
		return posted;
	}

	public String getPosttime() 
	{
		return posttime;
	}

	public void setPosted(String posted) 
	{
		this.posted = posted;
	}

	public void setPosttime(String posttime) 
	{
		this.posttime = posttime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
    
}
