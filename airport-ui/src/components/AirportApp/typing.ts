export enum MenuItemType {
    AIRPORT_SEARCH,
    ADMIN_PANEL,
}

export type ProviderDetail = {
    name: string;
    price: string;
    currency: string;
};

export enum JobStatus {
    STARTING = 'STARTING',
    STARTED = 'STARTED',
    STOPPING = 'STOPPING',
    STOPPED = 'STOPPED',
    FAILED = 'FAILED',
    COMPLETED = 'COMPLETED',
    ABANDONED = 'ABANDONED',
}
