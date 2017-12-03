package reports;

import model.TeamManager;

public class TeamManagerReport implements IReport{
    private TeamManager teamManager;

    public TeamManagerReport(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    @Override
    public void getReport() {
        System.out.println(teamManager);
        System.out.println("\nLists of subordinates:");
        teamManager.getEmployees()
                .forEach(e-> e.reportWork().getReport());
        System.out.println("========================");
    }
}
