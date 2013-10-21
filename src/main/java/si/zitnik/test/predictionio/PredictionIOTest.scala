package si.zitnik.test.predictionio

import io.prediction.Client

/**
 * Created with IntelliJ IDEA.
 * User: slavkoz
 * Date: 21/10/13
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
object PredictionIOTest {
  private val key = "t5F3z64loCnljCdEQoYk4V7TaCZEuRrvZSzNDXATQpI9xSD9F9hw9G59d8MG7vrN"
  private var client: Client = null

  def createDomain() {
    //1. Create Users
    client.createUser("Mirco")
    client.createUser("Johan")
    client.createUser("Jurcek")
    client.createUser("XWoman")

    //2. Create items
    client.createItem("mirco", Array("person"))
    client.createItem("johan", Array("person"))
    client.createItem("jurcek", Array("person"))
    client.createItem("xwoman", Array("person"))

  }

  def recordActions() {
    //Add actions
    println("User %s views user %s".format("mirco", "johan"))
      client.identify("Mirco")
      client.userActionItem("view", "johan")
    println("User %s views user %s".format("johan", "jurcek"))
      client.identify("Johan")
      client.userActionItem("view", "jurcek")
    println("User %s views user %s".format("jurcek", "johan"))
      client.identify("Jurcek")
      client.userActionItem("view", "johan")
    println("User %s views user %s".format("jurcek", "mirco"))
      client.identify("Jurcek")
      client.userActionItem("view", "mirco")

  }

  def predictActions() {
    println("Retrieve top 1 recommendations for user %s".format("Mirco"))
      client.identify("Mirco")
      println("Recommendations: %s".format(client.getItemRecTopN("FutureFriendsEngine", 1).mkString))
    println("Retrieve top 1 recommendations for user %s".format("XWoman"))
      client.identify("XWoman")
      println("Recommendations: %s".format(client.getItemRecTopN("FutureFriendsEngine", 1).mkString))
  }

  def main(args: Array[String]) {
    client = new Client(key)

    //1. Create domain
    //createDomain()

    //2. Record actions
    //recordActions()

    //wait some time for the server to process data ....
    //Thread.sleep(60000)

    //3. predict actions
    predictActions()

    client.close()
  }

}
