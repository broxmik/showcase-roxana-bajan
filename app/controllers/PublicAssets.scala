package controllers

import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global
import com.google.inject.Inject
import play.api.mvc._
import services.ResourceService
import utils.Errors

/**
 * Controller that handles fetching elements from the public assets
 *
 * @author Roxana
 * @version 1.0
 */
object PublicAssets extends Controller{

  @Inject
  private val resourceService: ResourceService = null

  /**
   * Fetch the production file.
   * If an error occurs, show a customized error page
   */
  def at(path:String, file:String) = Action.async { implicit request =>
    Assets.at(path, file).apply(request).map(result => {
      // Test the status
      result.header.status match {
        // No error just send the result
        case x if x < 400 => result
        // Specific 404 error
        case x if x == 404 => NotFound(views.html.errors(Errors.TITLE_ERROR_404, Errors.MESSAGE_ERROR_404))
        // Other kind of errors
        case _ => InternalServerError(views.html.errors(Errors.TITLE_ERROR_500, Errors.MESSAGE_ERROR_500))
      }
    })
  }

}
