package com.healthcode.healthcodeserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Objects;


@TableName("t_account")
public class Account {
  @TableId
  private String accountId;
  private String username;
  private String password;
  //category:0 用户，1 核酸检测人员，2 防疫管理人员
  private int category;

  public Account() {
  }

  public Account(String accountId, String username, String password) {
    this.accountId = accountId;
    this.username = username;
    this.password = password;
  }

  public String getAccountId() {
    return accountId;
  }

  public String getUsername() {
    return username;
  }

  public int getCategory() {
    return category;
  }

  public String getPassword() {
    return password;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setCategory(int category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return "Account{" +
            "accountId='" + accountId + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", category=" + category +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Account account = (Account) o;
    return category == account.category
            && Objects.equals(accountId, account.accountId)
            && Objects.equals(username, account.username)
            && Objects.equals(password, account.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId, username, password, category);
  }
}
