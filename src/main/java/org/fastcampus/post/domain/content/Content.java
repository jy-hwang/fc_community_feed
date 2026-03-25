package org.fastcampus.post.domain.content;

import org.fastcampus.post.domain.common.DatetimeInfo;

public abstract class Content {
  final DatetimeInfo datetimeInfo;
  String contentText;

  protected Content(String contentText) {
    checkText(contentText);
    this.contentText = contentText;
    this.datetimeInfo = new DatetimeInfo();
  }

  protected abstract void checkText(String contentText);

  public String getContentText() {
    return contentText;
  }

  public void updateContent(String updateContent) {
    checkText(updateContent);
    this.contentText = updateContent;
    this.datetimeInfo.updateEditDatetime();
  }
}
