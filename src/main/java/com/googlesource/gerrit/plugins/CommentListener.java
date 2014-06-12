package com.googlesource.gerrit.plugins;

import com.google.gerrit.common.ChangeListener;
import com.google.gerrit.extensions.annotations.Listen;
import com.google.gerrit.reviewdb.client.Change;
import com.google.gerrit.server.data.AccountAttribute;
import com.google.gerrit.server.data.ChangeAttribute;
import com.google.gerrit.server.events.ChangeEvent;
import com.google.gerrit.server.events.CommentAddedEvent;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Listen
@Singleton
public class CommentListener implements ChangeListener {

  private static final Logger log = LoggerFactory.getLogger(CommentListener.class);

  @Override
  public void onChangeEvent(ChangeEvent event) {
    if (event instanceof CommentAddedEvent) {
      CommentAddedEvent caEvent = (CommentAddedEvent) event;
      ChangeAttribute changeAttribute = caEvent.change;
      String project = changeAttribute.project;
      String status = changeAttribute.status.toString();
      log.debug("project: " + project);
      log.debug("change status: " + status);

      AccountAttribute author = caEvent.author;
      String commentor = author.name;
      log.debug("name: " + commentor);

      String comment = caEvent.comment;
      log.debug("comment: " + comment);

      String[] s = new String[]{"/home/gerrit/bin/comment-hook.sh", project, status, commentor, comment};
      try {
        Process p = Runtime.getRuntime().exec(s);
      } catch (Exception e) {
        log.error(e.getMessage());
      }
    }
  }
}
