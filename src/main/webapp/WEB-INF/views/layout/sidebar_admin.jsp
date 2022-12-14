
  <aside class="left-sidebar sidebar-dark" id="left-sidebar">
    <div id="sidebar" class="sidebar sidebar-with-footer">

      <!-- SIDEBAR HEADER -->
      <div class="app-brand">
        <a href="#">
          <img src="/img/logo.png" alt="Routine">
          <span class="brand-name">Routine</span>
        </a>
      </div>
      <!-- SIDEBAR HEADER END -->

      <!-- SIDEBAR MENU -->
      <div class="sidebar-left" data-simplebar style="height: 100%;">
        <ul class="nav sidebar-inner" id="sidebar-menu">

          <!-- Member -->
          <li class="section-title">
            <i class="mdi mdi-account"></i>
            User
          </li>
          <li>
            <a class="sidenav-item-link" href="/admin/user-list?level=member">
              <span class="nav-text">Member List</span>
            </a>
          </li>
          <li>
            <a class="sidenav-item-link" href="/admin/user-list?level=admin">
              <span class="nav-text">Admin List</span>
            </a>
          </li>

          <!-- Board -->
          <li class="section-title">
            <i class="mdi mdi-bulletin-board"></i>
            Board
          </li>
          <li>
            <a class="sidenav-item-link" href="/admin/board-list?boardCategory=notice">
              <span class="nav-text">Notice List</span>
            </a>
          </li>
          <li>
            <a class="sidenav-item-link" href="/admin/board-list?boardCategory=FAQ">
              <span class="nav-text">FAQ List</span>
            </a>
          </li>
          <li>
            <a class="sidenav-item-link" href="/admin/board-list?boardCategory=QnA">
              <span class="nav-text">QnA List</span>
            </a>
          </li>

          <!-- Routine -->
          <li class="section-title">
            <i class="mdi mdi-progress-clock"></i>
            Routine
          </li>
          <li>
            <a class="sidenav-item-link" href="/admin/routine-list">
              <span class="nav-text">Routine List</span>
            </a>
          </li>
          <li>
            <a class="sidenav-item-link" href="/admin/mission-list">
              <span class="nav-text">Mission List</span>
            </a>
          </li>

          <!-- User Page -->
          <br/>
          <li class="has-sub">
            <a class="sidenav-item-link"href="javascript:void(0)" data-toggle="collapse" 
              data-target="#user"aria-expanded="false" aria-controls="user">
              <i class="mdi mdi-label-variant"></i>
              <span class="nav-text">User Page</span>
              <b class="caret"></b>
            </a>
            <ul class="collapse" id="user" data-parent="#sidebar-menu">
              <div class="sub-menu">
                <li>
                  <a class="sidenav-item-link" href="/profile/login">
                    <span class="nav-text">Profile Login</span>
                  </a>
                </li>
                <li>
                  <a class="sidenav-item-link" href="/profile/join">
                    <span class="nav-text">Profile Join</span>
                  </a>
                </li>
                <li>
                  <a class="sidenav-item-link" href="/routine/routine-list">
                    <span class="nav-text">Routine List</span>
                  </a>
                </li>
                <li>
                  <a class="sidenav-item-link" href="#">
                    <span class="nav-text">Temp</span>
                  </a>
                </li>
                <li>
                  <a class="sidenav-item-link" href="#">
                    <span class="nav-text">Temp</span>
                  </a>
                </li>
                <li>
                  <a class="sidenav-item-link" href="#">
                    <span class="nav-text">Temp</span>
                  </a>
                </li>
              </div>
            </ul>
          </li>

        </ul>
      </div>
      <!-- SIDEBAR MENU END -->

      <!-- SIDEBAR FOOTER -->
      <div class="sidebar-footer">
        <div class="sidebar-footer-content">
          <ul class="d-flex">
            <li>
              <a href="#" data-toggle="tooltip" title="Profile settings">
                <i class="mdi mdi-settings"></i>
              </a>
            </li>
            <li>
              <a href="#" data-toggle="tooltip" title="No chat messages">
                <i class="mdi mdi-chat-processing"></i>
              </a>
            </li>
          </ul>
        </div>
      </div>
      <!-- SIDEBAR FOOTER END -->

    </div>
  </aside>
