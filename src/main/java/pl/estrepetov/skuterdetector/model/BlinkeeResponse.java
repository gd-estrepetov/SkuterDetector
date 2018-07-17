package pl.estrepetov.skuterdetector.model;

import java.util.List;

public class BlinkeeResponse {
  private List<SkuterInfo> data;

  public BlinkeeResponse() {
  }

  public List<SkuterInfo> getData() {
    return data;
  }

  public void setData(List<SkuterInfo> data) {
    this.data = data;
  }
}