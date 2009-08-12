/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hdfs.server.common;

/************************************
 * Some handy internal HDFS constants
 *
 ************************************/

public interface HdfsConstants {
  /**
   * Type of the node
   */
  static public enum NodeType {
    NAME_NODE,
    DATA_NODE;
  }

  /** Startup options */
  static public enum StartupOption{
    FORMAT  ("-format"),
    REGULAR ("-regular"),
    BACKUP  ("-backup"),
    CHECKPOINT("-checkpoint"),
    UPGRADE ("-upgrade"),
    ROLLBACK("-rollback"),
    FINALIZE("-finalize"),
    IMPORT  ("-importCheckpoint");
    
    private String name = null;
    private StartupOption(String arg) {this.name = arg;}
    public String getName() {return name;}
    public NamenodeRole toNodeRole() {
      switch(this) {
      case BACKUP: 
        return NamenodeRole.BACKUP;
      case CHECKPOINT: 
        return NamenodeRole.CHECKPOINT;
      default:
        return NamenodeRole.ACTIVE;
      }
    }

  }

  // Timeouts for communicating with DataNode for streaming writes/reads
  public static int READ_TIMEOUT = 60 * 1000;
  public static int WRITE_TIMEOUT = 8 * 60 * 1000;
  public static int WRITE_TIMEOUT_EXTENSION = 5 * 1000; //for write pipeline

  /**
   * Defines the NameNode role.
   */
  static public enum NamenodeRole {
    ACTIVE    ("NameNode"),
    BACKUP    ("Backup Node"),
    CHECKPOINT("Checkpoint Node"),
    STANDBY   ("Standby Node");

    private String description = null;
    private NamenodeRole(String arg) {this.description = arg;}
  
    public String toString() {
      return description;
    }
  }
  
  /**
   * Define Replica Type
   */
  static public enum ReplicaState {
    FINALIZED,  // finalized replica
    RBW,        // replica being written
    RWR,        // replica waiting to be recovered
    RUR,        // replica under recovery
    TEMPORARY   // temporary replica
  }
}

