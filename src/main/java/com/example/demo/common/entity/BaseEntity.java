package com.example.demo.common.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Data;

/**
 * テーブルの共通項目を定義したクラスです。</br>
 * 全てのEntityクラスはこのクラスを継承して作成します。
 */
@MappedSuperclass
@Data
public class BaseEntity {

  /** データ登録日時 */
  @Column(name = "created_at")
  @Temporal(TemporalType.DATE)
  private Date createdAt;

  /** データ更新日時 */
  @Column(name = "updated_at")
  @Temporal(TemporalType.DATE)
  private Date updatedAt;

  /**
   * データ登録前に共通的に実行されるメソッド
   */
  @PrePersist
  public void preInsert() {
    Date date = new Date();
    setCreatedAt(date);
    setUpdatedAt(date);
  }

  /**
   * データ更新前に共通的に実行されるメソッド
   */
  @PreUpdate
  public void preUpdate() {
    setUpdatedAt(new Date());
  }

}