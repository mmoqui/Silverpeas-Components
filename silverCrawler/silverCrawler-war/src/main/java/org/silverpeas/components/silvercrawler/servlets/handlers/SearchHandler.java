/*
 * Copyright (C) 2000 - 2024 Silverpeas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * As a special exception to the terms and conditions of version 3.0 of
 * the GPL, you may redistribute this Program in connection with Free/Libre
 * Open Source Software ("FLOSS") applications as described in Silverpeas's
 * FLOSS exception. You should have received a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "https://www.silverpeas.org/legal/floss_exception.html"
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.silverpeas.components.silvercrawler.servlets.handlers;

import org.silverpeas.components.silvercrawler.control.SilverCrawlerSessionController;
import org.silverpeas.components.silvercrawler.model.FileDetail;
import org.silverpeas.kernel.util.StringUtil;
import org.silverpeas.core.util.file.FileUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Handler for use case : user submitted search query.
 * @author Ludovic Bertin
 */
public class SearchHandler extends FunctionHandler {

  @Override
  public String getDestination(SilverCrawlerSessionController sessionController,
      HttpServletRequest request) throws Exception {

    String searchResult = FileUtil.checkTaintedData(request.getParameter("Id"));
    if (StringUtil.isDefined(searchResult)) {
      sessionController.setCurrentPathFromResult(searchResult);
      // Go back to main page
      return HandlerProvider.getHandler("ViewDirectory").getDestination(sessionController, request);
    }

    // Retrieves search query
    String wordSearch = request.getParameter("WordSearch");
    // Performs search
    Collection<FileDetail> docs = sessionController.getResultSearch(wordSearch);

    // Stores results in request as attributes
    request.setAttribute("Path", sessionController.getPath());
    request.setAttribute("Docs", docs);
    request.setAttribute("Word", wordSearch);

    // returns page to redirect to
    return "viewResultSearch.jsp?ArrayPaneAction=ChangePage&ArrayPaneTarget=docs&PaginationPaneIndex=0";
  }
}
