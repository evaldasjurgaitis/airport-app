export enum MenuItemType {
    AIRPORT_SEARCH,
    ADMIN_PANEL,
}

export type CountryDetail = {
    code: string;
    name: string;
};

export type RegionDetail = {
    code: string;
    name: string;
};

export type AirportDetail = {
    id: number;
    type: string | null;
    name: string | null;
    latitude: string | null;
    longitude: string | null;
    altitude: string | null;
    continent: string | null;
    isoCountry: string | null;
    isoRegion: string | null;
    municipality: string | null;
    sheduledService: boolean;
    gpsCode: string | null;
    iataCode: string | null;
    localCode: string | null;
    availableProviders: ProviderDetail[];
    providerDetail: ProviderDetail;
};

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

export type Pagination = {
    current: number;
    pageSize: number;
    total: number;
};
